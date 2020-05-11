import React from 'react';
import Producto from './Producto';

class Listado extends React.Component {

    render() {
        const _props = this.props;
        return (
            <div className="row">
                <div className="col-12">
                    <div className="row mt-3">
                        <div className="col-12">
                            <table className="table">
                                <thead>
                                    <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Código</th>
                                    <th scope="col">Descripción</th>
                                    <th scope="col">Precio</th>
                                    <th scope="col">Tipo Produto</th>
                                    <th></th>
                                    </tr>
                                </thead>
                                <tbody>                        
                                    {
                                        _props.productos.map(function(producto, index){
                                            return <Producto deleteProducto={_props.deleteProducto} key={index} producto={producto}></Producto>
                                        })
                                    }
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}
export default Listado;