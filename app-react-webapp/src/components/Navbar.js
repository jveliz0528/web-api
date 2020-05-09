import React from 'react';
class Navbar extends React.Component {

    render() {
        return (
            <div className="row">
                <div className="col-12">
                    <form onSubmit={this.props.logout}>
                        <div className="form-gruop">
                            <label>Usuario: {this.props.username}</label>
                        </div>
                        <button className="btn btn-info">Logout</button>
                    </form>
                </div>
            </div>
        );
    }
}
export default Navbar;