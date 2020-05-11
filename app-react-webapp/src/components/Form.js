import React from 'react';
import Option from './Option';
class Form extends React.Component {

    render() {

        //obtenemos los tipos de productos
        const tipoProducto = JSON.parse(localStorage.getItem('tipoProductos'));

        return (
            <div className="row mt-3">
                <div className="col-6">
                    <form onSubmit={this.props.findProductos}>
                        <div className="form-group">
                            <label htmlFor="bnombre">Nombre</label>
                            <input type="text" className="form-control" name="bnombre" id="bnombre"></input>
                        </div>
                        <button className="btn btn-primary">Buscar Productos</button>
                    </form>
                </div>
                <div className="col-6">
                    <form onSubmit={this.props.createProducto}>                
                        <div className="form-group">
                            <label htmlFor="ecodigo">Codigo</label>
                            <input type="text" className="form-control" name="codigo" id="ecodigo"></input>
                        </div>
                        <div className="form-group">
                            <label htmlFor="epassword">Nombre</label>
                            <input type="text" className="form-control" id="epassword" name="nombre"></input>
                        </div>
                        <div className="form-group">
                            <label htmlFor="eprecio">Precio</label>
                            <input type="text" className="form-control" name="precio" id="eprecio"></input>
                        </div>
                        <div className="form-group">
                            <select className="custom-select" id="tipoProducto" aria-label="Example select with button addon">
                                    {
                                        tipoProducto.map(function(tipo, index){
                                            return <Option key={index} tipo={tipo}></Option>
                                        })
                                    }
                            </select>
                        </div>
                        <button className="btn btn-success">Grabar</button>
                    </form>
                </div>
            </div>
        );
    }
}

export default Form;