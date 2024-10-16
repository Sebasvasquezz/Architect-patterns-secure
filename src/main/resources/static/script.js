const apiBaseUrl = 'https://localhost:8443/properties'; 

let selectedPropertyId = null;

function logout() {
    localStorage.removeItem('loggedIn');
    window.location.href = 'login.html';
}

async function createProperty() {
    const property = {
        address: document.getElementById('address').value,
        price: document.getElementById('price').value,
        size: document.getElementById('size').value,
        description: document.getElementById('description').value
    };

    const response = await fetch(apiBaseUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(property),
    });

    if (response.ok) {
        alert('Propiedad creada exitosamente');
        clearForm();
        listProperties();
    } else {
        alert('Error al crear la propiedad');
    }
}

async function listProperties() {
    const response = await fetch(apiBaseUrl);
    const properties = await response.json();
    const propertyTableBody = document.querySelector('#propertyTable tbody');
    propertyTableBody.innerHTML = '';
    properties.forEach(property => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${property.id}</td>
            <td>${property.address}</td>
            <td>$${property.price}</td>
            <td>${property.size} m²</td>
            <td>${property.description}</td>
            <td>
                <button onclick="editProperty(${property.id})">Editar</button>
                <button onclick="deleteProperty(${property.id})">Eliminar</button>
            </td>
        `;
        propertyTableBody.appendChild(row);
    });
}

async function deleteProperty(id) {
    if (confirm('¿Estás seguro de que deseas eliminar esta propiedad?')) {
        const response = await fetch(`${apiBaseUrl}/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            alert('Propiedad eliminada exitosamente');
            listProperties();
        } else {
            alert('Error al eliminar la propiedad');
        }
    }
}

async function editProperty(id) {
    const response = await fetch(`${apiBaseUrl}/${id}`);
    const property = await response.json();
    document.getElementById('address').value = property.address;
    document.getElementById('price').value = property.price;
    document.getElementById('size').value = property.size;
    document.getElementById('description').value = property.description;

    selectedPropertyId = id;

    document.getElementById('addButton').style.display = 'none';
    document.getElementById('updateButton').style.display = 'block';
}

async function updateProperty() {
    if (selectedPropertyId === null) return;

    const property = {
        address: document.getElementById('address').value,
        price: document.getElementById('price').value,
        size: document.getElementById('size').value,
        description: document.getElementById('description').value
    };

    const response = await fetch(`${apiBaseUrl}/${selectedPropertyId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(property),
    });

    if (response.ok) {
        alert('Propiedad actualizada exitosamente');
        clearForm();
        listProperties();
        selectedPropertyId = null;

        document.getElementById('addButton').style.display = 'block';
        document.getElementById('updateButton').style.display = 'none';
    } else {
        alert('Error al actualizar la propiedad');
    }
}

function clearForm() {
    document.getElementById('propertyForm').reset();
    selectedPropertyId = null;

    document.getElementById('addButton').style.display = 'block';
    document.getElementById('updateButton').style.display = 'none';
}

window.onload = listProperties;
