import React, { useEffect, useState } from 'react';
import apiClient from './apiClient';

const MapComponent = ({ onClose }) => {
    const [location, setLocation] = useState(null);
    const [pincodes, setPincodes] = useState([]);

    useEffect(() => {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                (position) => {
                    setLocation({
                        latitude: position.coords.latitude,
                        longitude: position.coords.longitude,
                    });
                },
                (error) => {
                    console.error(error);
                }
            );
        }
    }, []);

    useEffect(() => {
        if (location) {
            const fetchData = async () => {
                const { data } = await apiClient.get('/posting/get/all');
                const filteredData = data.filter((posting) => posting.pincode !== null);
                const pincodes = filteredData.map((posting) => posting.pincode);
                setPincodes(pincodes);

                // Initialize Google Maps API
                const googleMapsScript = document.createElement('script');
                googleMapsScript.src = `https://maps.googleapis.com/maps/api/js?key=AIzaSyD8GED_UGjPTaqX1UcI957lovSsquUUSPo&callback=initMap`;
                googleMapsScript.async = true;
                googleMapsScript.defer = true;
                document.head.appendChild(googleMapsScript);

                // Add initMap function to window object
                window.initMap = () => {
                    const map = new window.google.maps.Map(document.getElementById('map'), {
                        center: { lat: location.latitude, lng: location.longitude },
                        zoom: 15,
                    });

                    // Add user marker
                    const userMarker = new window.google.maps.Marker({
                        position: { lat: location.latitude, lng: location.longitude },
                        map,
                        title: 'You are here',
                        label: 'U',
                    });

                    // Add markers for pincodes
                    pincodes.forEach((pincode) => {
                        const geocoder = new window.google.maps.Geocoder();
                        geocoder.geocode({ address: pincode }, (results, status) => {
                            if (status === 'OK') {
                                const marker = new window.google.maps.Marker({
                                    map,
                                    position: results[0].geometry.location,
                                    title: pincode,
                                    label: 'P',
                                });
                            } else {
                                console.error(`Geocode was not successful for the following reason: ${status}`);
                            }
                        });
                    });
                };
            };

            fetchData();
        }
    }, [location]);

    return (
        <div>
            <h1>Map Component</h1>
            <div id="map" style={{ width: '100%', height: '500px' }} />
        </div>
    );
};

export default MapComponent;

//AIzaSyBd_a4RP34XxbMuYLN21frg3Tb2RNfylrU
