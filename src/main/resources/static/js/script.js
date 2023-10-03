// Function to handle the authentication request
        function authenticate() {
            fetch('http://localhost:8080/auth/login', {
                method: 'POST',
                // Include any necessary request parameters
            })
                .then(response => {

                        const bearerToken = response.headers.get('Authorization');
                        localStorage.setItem('token', bearerToken);

                        // Hide the "Authenticate" button and show the "Fetch Protected Data" button
                        document.getElementById('authenticateButton').style.display = 'none';
                        document.getElementById('fetchDataButton').style.display = 'block';

                })
                .catch(error => {
                    console.error('Error:', error);
                });
        }

// Function to fetch protected data using the stored token
        function fetchData() {
            const token = localStorage.getItem('token');

            if (token) {
                fetch('http://localhost:8080/**', {
                    headers: {
                        'Authorization': token,
                        // Add any other headers and request parameters as needed
                    }
                })
                    .then(response => {
                        if (response.ok) {
                            return response.json();
                        } else {
                            throw new Error('Request failed');
                        }
                    })
                    .then(data => {
                        // Process the data from the protected resource
                        console.log(data);
                    })
                    .catch(error => {
                        console.error('Error:', error);
                    });
            } else {
                console.error('Bearer token not found');
            }
        }
