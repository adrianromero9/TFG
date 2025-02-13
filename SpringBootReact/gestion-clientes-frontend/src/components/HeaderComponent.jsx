import React from 'react';

export const HeaderComponent = () => {
    return (
        <div>
            <header>
                <nav className="navbar navbar-expands-bd navbar-dark bg-dark"></nav>
                <div>
                    <a href="/" className="navbar-brand">Gestión de Clientes</a>
                </div>
            </header>
        </div>
    )

}

export default HeaderComponent;