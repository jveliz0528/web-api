import React from 'react';
class Login extends React.Component {

    render() {
        return (
            <div className="row">
                <div className="col-4">
                    <form onSubmit={this.props.login}>
                        <div className="form-group">
                            <label>User Name</label>
                            <input name="username" id="username" type="text" className="form-control"></input>
                        </div>
                        <div className="form-group">
                            <label>Password</label>
                            <input name="password" id="password" type="password" className="form-control"></input>
                        </div>
                        <button className="btn btn-primary">Login</button>
                    </form>
                </div>
            </div>
        );
    }
}
export default Login;