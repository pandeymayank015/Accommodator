import React from 'react';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';

function Features() {
    const cards = [
        {
            id: 2,
            title: 'Roommate Matching',
            description: 'Click to find a roommate that shares your interests.',
            image:'https://www.udr.com/globalassets/corporate/other/roommatefinder/roommatefinder_logo_final.png',
            buttonLabel: 'Click here',
            buttonLink: '/StudentPreferences',

        },
        {
            id: 3,
            title: 'See Listings',
            description: 'Click to see all the available listings',
            image:'https://cdn.w600.comps.canstockphoto.com/new-listing-stamp-eps-vector_csp32695784.jpg',
            buttonLabel: 'Click here',
            buttonLink: '/ShowPosting',

        },
        {
            id: 4,
            title: 'Map',
            description: 'Click to see available listings on a map',
            image:'https://media.istockphoto.com/id/176022505/photo/halifax-nova-scotia-canada-on-a-map.jpg?s=1024x1024&w=is&k=20&c=03_5xhwEj6UvrQeHtOqrjyhlP_zHGudJDMyLtDIOfcA=',
            buttonLabel: 'Click here',
            buttonLink: '/GoogleMap',

        },
        {
            id: 5,
            title: 'See favorites',
            description: 'Click to see postings added to your favorites',
            image:'https://cdn.thememylogin.com/uploads/edd/2019/03/favorites.png',
            buttonLabel: 'Click here',
            buttonLink: '/ShowFavorites',

        },
    ];

    return (
        
        <div className="auth-wrapper">
            <div className="form-container">

        <div className="card-deck">
            {cards.map((card) => (
                <Card key={card.id}>
                    <Card.Img variant="top" src={card.image} alt={card.title} style={{ width: '610px', height: '270px' }}/>
                    <Card.Body>
                        <Card.Title>{card.title}</Card.Title>
                        <Card.Text>{card.description}</Card.Text>
                        <Button variant="primary" href={card.buttonLink}>
                            {card.buttonLabel}
                        </Button>
                    </Card.Body>
                </Card>
            ))}
        </div>
        </div>
            </div>


    );
}

export default Features;
