import React from 'react';
import Login from './Login';
import Navbar from './Navbar';
import Listado from './Listado'
import Form from './Form';
import axios from 'axios';

class Layout extends React.Component {

    constructor() {
        super();
        let token = localStorage.getItem('Access-Token');

        //estado del componente Layout. contiene un atributo productos vacio
        this.state = {
            logged: token != null,
            productos:[]
        }
    }
    
    componentDidMount() {
        console.log('componentDidMount');
        if(this.state.logged) {
            this.findAllProductos();
        }
    }

    login = event => {
        debugger;
        event.preventDefault();
        //axios 
        axios.post(
            `http://localhost:8080/app-ws-rest/api/auth?username=${event.target.elements.username.value}&password=${event.target.elements.password.value}`,
        ).then(res => {

            localStorage.setItem('Access-Token', res.headers['access-token']);
            //lamada nuevamente a la lista de productos
            axios.get(`http://localhost:8080/app-ws-rest/api/productos`)
                .then(res => {
                    const productos = res.data;
                    this.setState(
                        {
                            logged: true,
                            productos: productos
                        }
                    );
                }
                );
        })
    }

    logout = async (e) => {
        e.preventDefault();
        localStorage.removeItem('Access-Token');
        this.setState(
            {
                logged: false,
                productos: []
            }
        );
    }

    //function para buscar la lista de productos
    findProductos = async (e) => {

        e.preventDefault();

        const nombre = e.target.elements.bnombre.value;
        let api_call;
        if (nombre === "") {
            api_call = `http://localhost:8080/app-ws-rest/api/productos`;
        } else {
            api_call = `http://localhost:8080/app-ws-rest/api/productos/filtro/${nombre}`;
        }
        axios.get(api_call)
            .then(res => {
                const productos = res.data;
                this.setState(
                    {
                        productos: productos
                    }
                );
                console.log(this.state);
            }
            );
    }

    findAllProductos() {

        let api_call = `http://localhost:8080/app-ws-rest/api/productos`;

        axios.get(api_call)
            .then(res => {
                const productos = res.data;
                this.setState(
                    {
                        productos: productos
                    }
                );
            }
            );
    }

    deleteProducto = async (producto) => {

        if (!window.confirm('Desea eliminar el producto')) {
            return false
        }
        axios.delete(
            'http://localhost:8080/app-ws-rest/api/productos/' + producto,
            {
                headers: {
                    Authorization: 'Basic ' + localStorage.getItem('Access-Token'),
                }
            }
        ).then(res => {
            this.findAllProductos();
        });
    }

    createProducto = async (e) => {

        e.preventDefault();

        let jsonObject = {
            codigo: e.target.elements.codigo.value,
            descripcion: e.target.elements.nombre.value,
            precio: e.target.elements.precio.value,
            tipoProducto: {
                id: e.target.elements.tipoProducto.value
            }
        }

        axios.post(
            'http://localhost:8080/app-ws-rest/api/productos',
            jsonObject,
            {
                headers: {
                    Authorization: 'Basic ' + localStorage.getItem('Access-Token'),
                }
            }
        ).then(res => {
            this.findAllProductos();
        });
    }

    render() {
        return (
            <div className="container-fluid">
                {
                    !this.state.logged &&
                    <Login
                        login={this.login}>
                    </Login>
                }
                {
                    this.state.logged &&
                    <Navbar
                        logout={this.logout}>
                    </Navbar>
                }
                {
                    this.state.logged &&
                    <Form
                        productos={this.state.productos}
                        findProductos={this.findProductos}
                        createProducto={this.createProducto}
                        deleteProducto={this.deleteProducto}>
                    </Form>
                }
                {
                    this.state.logged &&
                    <Listado
                        productos={this.state.productos}
                        deleteProducto={this.deleteProducto}>
                    </Listado>
                }
            </div>
        );
    }
}

export default Layout;