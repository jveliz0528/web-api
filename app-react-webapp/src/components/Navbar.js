import React from 'react';
class Navbar extends React.Component {

    constructor() {
        super();
        // toma el hash grabado en localstorage
        let base64 = atob(localStorage.getItem('Access-Token'));
        let username = base64.split(":")[0];
        localStorage.setItem("username", username);
        this.state = {
            username : username
        };
    }

    render() {
        
        return (
            <div className="row">
                <div className="col-12">
                    <form onSubmit={this.props.logout}>
                        <div className="form-gruop">
                            <label>Usuario: {this.state.username}</label>
                            <button className="btn btn-info pull-right">Logout</button>
                        </div>
                    </form>
                </div>
            </div>
        );
    }
}
export default Navbar;