import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';
import Image from 'react-bootstrap/Image';
import Spinner from 'react-bootstrap/Spinner';
import Alert from 'react-bootstrap/Alert';
import paginationFactory from 'react-bootstrap-table2-paginator';
import BootstrapTable from 'react-bootstrap-table-next';
import apiClient from './apiClient';

function DataTable() {
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        loadData();
    }, []);

    function loadData() {
        apiClient
            .get('http://csci5308vm25.research.cs.dal.ca:8080/student/get/all')
            .then((response) => {
                setData(response.data);
                setLoading(false);
            })
            .catch((error) => {
                setError(error);
                setLoading(false);
            });
    }

    function handleApprove(studentId) {
        apiClient
            .put(`http://csci5308vm25.research.cs.dal.ca:8080/admin/approve/${studentId}`, { status: 'approved' })
            .then((response) => {
                loadData(); // Reload data after action
            })
            .catch((error) => {
                setError(error);
            });
    }

    function handleReject(studentId) {
        apiClient
            .put(`http://csci5308vm25.research.cs.dal.ca:8080/admin/reject/${studentId}`, { status: 'rejected' })
            .then((response) => {
                loadData(); // Reload data after action
            })
            .catch((error) => {
                setError(error);
            });
    }

    const columns = [
        { dataField: 'studentId', text: 'ID' },
        { dataField: 'firstName', text: 'First Name' },
        { dataField: 'lastName', text: 'Last Name' },
        { dataField: 'email', text: 'Email' },
        { dataField: 'offerLetter', text: 'Offer Letter', formatter: (cell) => <a href={URL.createObjectURL(new Blob([cell]))} target="_blank" rel="noopener noreferrer"><Image src={URL.createObjectURL(new Blob([cell]))} alt="Offer Letter" fluid rounded /></a> },
        {
            dataField: 'actions',
            text: 'Actions',
            formatter: (cell, row) => (
                <>
                    <Button variant="success" onClick={() => handleApprove(row.studentId)}>
                        Approve
                    </Button>{' '}
                    <Button variant="danger" onClick={() => handleReject(row.studentId)}>
                        Reject
                    </Button>
                </>
            ),
        },
    ];

    const paginationOptions = {
        sizePerPage: 10,
        hideSizePerPage: true,
        hidePageListOnlyOnePage: true,
    };

    return (
        <>
            {error && <Alert variant="danger">{error.message}</Alert>}
            {loading ? (
                <Spinner animation="border" variant="primary" />
            ) : (
                <div style={{ maxWidth: '80%', margin: 'auto', marginTop: '50px' }}>
                    <BootstrapTable keyField="studentId" data={data} columns={columns} pagination={paginationFactory(paginationOptions)} />
                </div>
            )}
        </>
    );
}

export default DataTable;
