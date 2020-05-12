import React from 'react';

/**
 * Producto recibo por props la funcion deleteProducto
 */
class Producto extends React.Component {

    render() {
        // console.log('Producto render');
        // console.log(this.props);

        return (

            <tr>
                <th scope="row">
                    {this.props.producto.id}
                </th>
                <th>
                    {this.props.producto.codigo}
                </th>
                <td>
                    {this.props.producto.descripcion}
                </td>
                <td>
                    {this.props.producto.precio}
                </td>
                <td>
                    {this.props.producto.tipoProducto.descripcion}
                </td>
                <td>
                    <button 
                        className="btn btn-danger" 
                        onClick={(e) =>this.props.deleteProducto(this.props.producto.codigo)}
                    >
                        Eliminar
                    </button>

                    <button 
                        className="btn btn-primary ml-2" 
                        onClick={(e) =>this.props.editarProducto(this.props.producto)}
                    >
                        Editar
                    </button>
                </td>
            </tr>
            
        );
    }
}

export default Producto;