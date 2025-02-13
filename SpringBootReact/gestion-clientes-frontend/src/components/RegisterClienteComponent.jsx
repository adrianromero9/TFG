import React, { useState } from 'react';
import axios from 'axios';

function RegisterClienteComponent({ onRegister }) {
  const [nombre, setNombre] = useState('');
  const [dni, setDni] = useState('');
  const [correo, setCorreo] = useState('');
  const [clave, setClave] = useState('');
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);

  const handleSubmit = async (event) => {
    event.preventDefault();
    setError(null);
    setSuccess(null);

    try {
      const response = await axios.post('http://localhost:8080/auth/register', {
        nombre,
        dni,
        correo,
        clave,
      });

      setSuccess('Registro exitoso. Ahora puedes iniciar sesión.');
      onRegister();
    } catch (error) {
      setError('Error al registrar. Verifica los datos e intenta de nuevo.');
      console.error('Error en registro:', error);
    }
  };

  return (
    <div>
      <h2>Registro de Cliente</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      {success && <p style={{ color: 'green' }}>{success}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="nombre">Nombre:</label>
          <input
            type="text"
            id="nombre"
            value={nombre}
            onChange={(e) => setNombre(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="dni">DNI:</label>
          <input
            type="text"
            id="dni"
            value={dni}
            onChange={(e) => setDni(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="correo">Correo:</label>
          <input
            type="email"
            id="correo"
            value={correo}
            onChange={(e) => setCorreo(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="clave">Clave:</label>
          <input
            type="password"
            id="clave"
            value={clave}
            onChange={(e) => setClave(e.target.value)}
            required
          />
        </div>
        <button type="submit">Registrarse</button>
      </form>
    </div>
  );
}

export default RegisterClienteComponent;
