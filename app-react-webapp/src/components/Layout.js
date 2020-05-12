import React from 'react';
import Login from './Login';
import Navbar from './Navbar';
import Listado from './Listado'
import Form from './Form';
import axios from 'axios';
import Mensajeria from './Mensajeria';

class Layout extends React.Component {

    constructor() {
        super();

        //obtiene eltoken desde el localstorage
        let token = localStorage.getItem('Access-Token');

        //estado del componente Layout. contiene un atributo productos vacio
        this.state = {
            mensaje: null,
            logged: token != null,
            productos:[]
        }
    }
    
    componentDidMount() {
        // console.log('componentDidMount');
        if(this.state.logged) {
            this.findAllProductos();
        }
    }

    login = event => {

        event.preventDefault();
        const _this = this;
        axios.post(
            `http://localhost:8080/app-ws-rest/api/auth?username=${event.target.elements.username.value}&password=${event.target.elements.password.value}`,
        ).then(res => {
            localStorage.setItem('Access-Token', res.headers['access-token']);
            
            //cargo los tipo de productos
            axios.get(`http://localhost:8080/app-ws-rest/api/tipoproductos`)
                .then(res => {
                    const tipoProductos = res.data;
                    localStorage.setItem('tipoProductos', JSON.stringify(tipoProductos));

                    this.findAllProductos();
                    //limpiar mensajeria: borrar el alerta
                    this.setState({
                        mensaje: null
                    });
                }
            );
        }).catch(function (error) {
            _this.setState({
                mensaje: "Usuario/Password inválido"
            });
        });
    }

    logout = async (e) => {
        e.preventDefault();

        //elimina el access token guardado en el localstorage
        localStorage.removeItem('Access-Token');
        localStorage.removeItem('tipoProductos');
        localStorage.removeItem('username');
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
                        logged: true,
                        productos: productos
                    }
                );
            }
        );
    }

    deleteProducto = async (producto) => {

        // debugger;
        if (!window.confirm('Desea eliminar el producto')) {
            return false
        }
        const _this = this;
        axios.delete('http://localhost:8080/app-ws-rest/api/productos/' + producto,
            {
                headers: {
                    Authorization: 'Basic ' + localStorage.getItem('Access-Token'),
                }
            }
        ).then(res => {
            this.findAllProductos();
        }).catch(function (error) {
            _this.setState(
                {
                    mensaje: "No se ha podido eliminar el producto"
                }
            );
            // alert("No se ha podido eliminar el producto...");
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
        
        const _this = this;
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
        }).catch(function (error) {
            // alert("No se ha podido crear el producto");
            _this.setState(
                {
                    mensaje: "No se ha podido dar de alta el producto"
                }
            );
            setTimeout(()=>_this.limpiarMensaje(_this), 3500);
        });
    }

    //EDITAR PRODUCTO
    editarProducto = async (producto) => {
       console.log(producto);
        producto.descripcion = producto.descripcion + ' editado'
        const _this = this;
        axios.put(
            'http://localhost:8080/app-ws-rest/api/productos',
            producto,
            {
                headers: {
                    Authorization: 'Basic ' + localStorage.getItem('Access-Token'),
                }
            }
        ).then(res => {
            this.findAllProductos();
        }).catch(function (error) {
            // alert("No se ha podido crear el producto");
            _this.setState(
                {
                    mensaje: "No se ha podido editar el producto"
                }
            );
            setTimeout(()=>_this.limpiarMensaje(_this), 3500);
        });
    }

    //LIMPIAR LA MENSAJERIA
    limpiarMensaje(_this) {
        _this.setState(
            {
                mensaje: null
            }
        );
    }

    render() {
        return (
            <div className="container-fluid mt-2">
                {
                   this.state.mensaje != null && 
                   <Mensajeria 
                        mensaje={this.state.mensaje}>
                   </Mensajeria>
                }
                {
                    !this.state.logged &&
                    <Login
                        login={this.login}>
                    </Login>
                }
                {
                    this.state.logged &&
                    <Navbar
                        username={this.username}
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
                        editarProducto={this.editarProducto}
                        deleteProducto={this.deleteProducto}>
                    </Listado>
                }
            </div>
        );
    }
}

export default Layout;