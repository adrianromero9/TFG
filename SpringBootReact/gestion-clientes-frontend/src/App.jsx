import { useState } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';
import HeaderComponent from './components/HeaderComponent';
import ListClientesComponent from './components/ListClientesComponent';
import FooterComponent from './components/FooterComponent';
import AddClienteComponent from './components/AddClienteComponent';
import LoginClienteComponent from './components/LoginClienteComponent';
import RegisterClienteComponent from './components/RegisterClienteComponent';

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  const handleLogin = (nombre, dni, clave) => {
    if (nombre && dni && clave) {
      setIsAuthenticated(true);
    }
  };

  return (
    <div>
      <BrowserRouter>
        <HeaderComponent isAuthenticated={isAuthenticated} />
        <div className="container">
          <Routes>
            <Route path="/" element={<ListClientesComponent />} />
            <Route path="/clientes" element={<ListClientesComponent />} />
            <Route path="/add-clientes" element={<AddClienteComponent />} />
            <Route path="/edit-clientes/:id_cliente" element={<AddClienteComponent />} />

            <Route path="/login" element={<LoginClienteComponent onLogin={handleLogin} />} />
            <Route path="/register" element={<RegisterClienteComponent />} />
          </Routes>
        </div>
        <FooterComponent />
      </BrowserRouter>
    </div>
  );
}

export default App;
