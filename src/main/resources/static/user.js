const apiAuthUrl = 'https://localhost:8443/api/users';

document.getElementById('loginForm')?.addEventListener('submit', function (event) {
    event.preventDefault();

    const username = document.getElementById('loginUsername').value;
    const password = document.getElementById('loginPassword').value;

    fetch(`${apiAuthUrl}/authenticate`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password }),
        credentials: 'include' 
    })
    .then(response => {
        if (response.ok) {
            localStorage.setItem('loggedIn', 'true');
            alert('Inicio de sesión exitoso');
            window.location.href = 'index.html'; 
        } else {
            alert('Credenciales inválidas');
        }
    })
    .catch(error => console.error('Error:', error));
});

document.getElementById('registerForm')?.addEventListener('submit', function (event) {
    event.preventDefault();

    const username = document.getElementById('regUsername').value;
    const password = document.getElementById('regPassword').value;

    fetch(`${apiAuthUrl}/register`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password }),
        credentials: 'include' 
    })
    .then(response => {
        if (response.ok) {
            alert('Registro exitoso');
            window.location.href = 'login.html'; 
        } else {
            alert('Error en el registro');
        }
    })
    .catch(error => console.error('Error:', error));
});

