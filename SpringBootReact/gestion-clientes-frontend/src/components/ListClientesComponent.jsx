import React, { useEffect, useState } from 'react';
import ClienteService from '../services/ClienteService';
import { Link } from 'react-router-dom';

export const ListClientesComponent = () => {
    const [clientes, setClientes] = useState([]);

    useEffect(() => {
        ClienteService.getAllClientes().then(response => {
            setClientes(response.data);
        }).catch(error => {
            console.log("Error al obtener clientes:", error);
        });
    }, []);

    const deleteCliente = (clienteId) => {
        ClienteService.deleteCliente(clienteId).then(() => {
            setClientes(clientes.filter(cliente => cliente.id_cliente !== clienteId));
        }).catch(error => {
            console.log("Error al eliminar cliente:", error);
        });
    };

    const formatFecha = (fecha) => {
        if (!fecha) return "No registrada";
        return fecha.split("T")[0];
    };

    return (
        <div className="container">
            <h2 className="text-center">Lista de Clientes</h2>
            <Link to="/add-clientes" className="btn btn-primary mb-2">Agregar Cliente</Link>
            <table className="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Correo</th>
                        <th>DNI</th>
                        {/* <th>Fecha Registro</th> */}
                        {/* <th>Clave</th> */}
                        <th>Notificaciones</th>
                        <th>Teléfono</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        clientes.map(cliente =>
                            <tr key={cliente.id_cliente}>
                                <td>{cliente.id_cliente}</td>
                                <td>{cliente.nombre}</td>
                                <td>{cliente.correo}</td>
                                <td>{cliente.dni}</td>
                                {/* <td>{(cliente.fecha_registro)}</td> */}
                                {/* <td>{cliente.clave}</td> */}
                                <td>{cliente.notificaciones ? "Sí" : "No"}</td>
                                <td>{cliente.telefono}</td>
                                <td>
                                    <Link className="btn btn-info" to={`/edit-clientes/${cliente.id_cliente}`}>Actualizar</Link>
                                    <button className="btn btn-danger" style={{ marginLeft: "10px" }} onClick={() => deleteCliente(cliente.id_cliente)}>Eliminar</button>
                                </td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    );
};

export default ListClientesComponent;
