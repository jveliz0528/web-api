
import React from 'react';

export class Mensajeria extends React.Component {

    render() {
        return (
            <div className="alert alert-danger" role="alert">
                {this.props.mensaje}
            </div>
        );
    }
}

export default Mensajeria;