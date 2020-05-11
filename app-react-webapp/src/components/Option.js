import React from 'react';

/**
 * Producto recibo por props la funcion deleteProducto
 */
class Option extends React.Component {

    render() {

        return (
            <option value={this.props.tipo.id}>{this.props.tipo.descripcion}</option>
        );
    }
}

export default Option;