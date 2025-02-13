import React, { useEffect, useState } from 'react';
import { useNavigate, Link, useParams } from 'react-router-dom';
import ClienteService from '../services/ClienteService';

export const AddClienteComponent = () => {
    const [nombre, setNombre] = useState('');
    const [correo, setCorreo] = useState('');
    const [dni, setDni] = useState('');
    // const [fechaRegistro, setFechaRegistro] = useState(new Date().toISOString().split("T")[0]); // Formato YYYY-MM-DD
    // const [clave, setClave] = useState('');
    const [notificaciones, setNotificaciones] = useState(false);
    const [telefono, setTelefono] = useState('');
    
    const navigate = useNavigate();
    const { id_cliente } = useParams();

    const saveOrUpdateCliente = async (e) => {
        e.preventDefault();

        const cliente = {
            nombre,
            correo,
            dni,
            // fechaRegistro,
            // clave,
            notificaciones,
            telefono
        };

        try {
            if (id_cliente) {
                await ClienteService.updateCliente(id_cliente, cliente);
                console.log("Cliente actualizado exitosamente");
            } else {
                await ClienteService.createCliente(cliente);
                console.log("Cliente agregado exitosamente");
            }
            navigate('/clientes');
        } catch (error) {
            console.error("Error al guardar cliente:", error);
        }
    };

    useEffect(() => {
        if (id_cliente) {
            ClienteService.getClienteById(id_cliente)
                .then(response => {
                    setNombre(response.data.nombre);
                    setCorreo(response.data.correo);
                    setDni(response.data.dni);
                    // setFechaRegistro(response.data.fechaRegistro);
                    // setClave(response.data.clave);
                    setNotificaciones(response.data.notificaciones);
                    setTelefono(response.data.telefono);
                })
                .catch(error => {
                    console.log(error);
                });
        }
    }, [id_cliente]);

    return (
        <div>
            <div className='container'>
                <div className='row'>
                    <div className='col-md-6 offset-md-3'>
                        <h2 className='text-center'>{id_cliente ? "Actualizar Cliente" : "Agregar Cliente"}</h2>
                        <div className='card-body'>
                            <form onSubmit={saveOrUpdateCliente}>
                                <div className='form-group mb-2'>
                                    <label className='form-label'>Nombre</label>
                                    <input type="text" className='form-control' value={nombre} onChange={(e) => setNombre(e.target.value)} required />
                                </div>
                                <div className='form-group mb-2'>
                                    <label className='form-label'>Correo</label>
                                    <input type="email" className='form-control' value={correo} onChange={(e) => setCorreo(e.target.value)} required />
                                </div>
                                <div className='form-group mb-2'>
                                    <label className='form-label'>DNI</label>
                                    <input type="text" className='form-control' value={dni} onChange={(e) => setDni(e.target.value)} required />
                                </div>
                                {/* <div className='form-group mb-2'>
                                    <label className='form-label'>Fecha de Registro</label>
                                    <input type="date" className='form-control' value={fechaRegistro} onChange={(e) => setFechaRegistro(e.target.value)} required />
                                </div> */}
                                {/* <div className='form-group mb-2'>
                                    <label className='form-label'>Clave</label>
                                    <input type="password" className='form-control' value={clave} onChange={(e) => setClave(e.target.value)} required />
                                </div> */}
                                <div className='form-group mb-2'>
                                    <label className='form-label'>Notificaciones</label>
                                    <input type="checkbox" checked={notificaciones} onChange={(e) => setNotificaciones(e.target.checked)} />
                                </div>
                                <div className='form-group mb-2'>
                                    <label className='form-label'>Teléfono</label>
                                    <input type="text" className='form-control' value={telefono} onChange={(e) => setTelefono(e.target.value)} required />
                                </div>
                                <button type="submit" className='btn btn-success'>Guardar</button>
                                &nbsp;&nbsp;
                                <Link to='/clientes' className="btn btn-danger">Cancelar</Link>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default AddClienteComponent;
