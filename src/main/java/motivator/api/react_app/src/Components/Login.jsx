import React from 'react';
import axios from 'axios';

export default class PersonList extends React.Component {
  state = {
    email: '',
    password: ''
  }

  handleChange = (event) => {
    const state = this.state
    state[event.target.name] = event.target.value;
    this.setState(state);
  }

  handleSubmit = event => {
    event.preventDefault();

    const user = {
      email: this.state.email,
      password: this.state.password
    };

    axios.post(`localhost:8080/login`, { user })
      .then(res => {
        console.log(res);
        console.log(res.data);
      })
  }

  render() {
    return (
      <div>
        <form onSubmit={this.handleSubmit}>
          <label>
            E-mail adress:
            <input type="email" name="email" onChange={this.handleChange} />
          </label>
          <label>
            Password:
            <input type="password" name="psw" onChange={this.handleChange} />
          </label>
          <button type="submit">Login</button>
        </form>
      </div>
    )
  }
}        
//<Link to="/Registration">Registration</Link>
