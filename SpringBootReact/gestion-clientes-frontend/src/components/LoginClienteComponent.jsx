import React, { useState } from "react";

function LoginClienteComponent({ onLogin }) {
  const [nombre, setNombre] = useState("");
  const [dni, setDni] = useState("");
  const [clave, setClave] = useState("");
  const [error, setError] = useState(null);

  const handleSubmit = async (event) => {
    event.preventDefault();
    setError(null);

    try {
      await onLogin(nombre, dni, clave);
    } catch (err) {
      setError("Error en el login.");
    }
  };

  return (
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
        <label htmlFor="clave">Clave:</label>
        <input
          type="password"
          id="clave"
          value={clave}
          onChange={(e) => setClave(e.target.value)}
          required
        />
      </div>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <button type="submit">Entrar</button>
    </form>
  );
}

export default LoginClienteComponent;
