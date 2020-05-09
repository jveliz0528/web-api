import React from 'react';
import Login from './Login';
import Navbar from './Navbar';
import Listado from './Listado'
import Form from './Form';
import axios from 'axios';

class Layout extends React.Component {

    constructor() {
        super();
        //estado del componente Layout. contiene un atributo productos vacio
        this.state = {
            logged: false,
            productos: []
        }
    }

    login = event => {
        debugger;
        event.preventDefault();
        //axios 
        axios.post(
            `http://localhost:8080/app-ws-rest/api/auth?username=${event.target.elements.username.value}&password=${event.target.elements.password.value}`,
            {},
            {
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        )
            .then(res => {

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
        if(nombre===""){
          api_call = `http://localhost:8080/app-ws-rest/api/productos`;
        }else {
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


    deleteProducto(producto) {
        console.log(producto);
    }

    createProducto = async (e) => {
        e.preventDefault();
        let jsonObject = {
            id: e.target.elements.id.value,
            nombre: e.target.elements.nombre.value,
            precio: e.target.elements.precio.value
        }
        console.log(jsonObject);

        // create a new XMLHttpRequest
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'http://localhost:8080/app-ws-rest/api/productos');
        xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
        xhr.send(JSON.stringify(jsonObject));

        //actualizo la lista de producros
        this.setState({
            productos: this.findProductos()
        });

        /*
        const response = await axios.post(
            'http://localhost:8080/app-ws-rest-server/api/productos',
            jsonObject,
            { headers: { 'Content-Type': 'application/json' } }
            );
        */

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