import React from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom'

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

    var email = this.state.email;
    var password = this.state.password;
    // var self = this;

    axios.post(`http://127.0.0.1:8080/login`, { email, password })
      .then(res => {
        console.log(res);
        console.log(res.data);
        if (res.status === 200) {
          window.location.replace('/Groups');
          // self.$router.push({path: '/Groups'});
        }
      });
  };

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
            <input type="password" name="password" onChange={this.handleChange} />
          </label>
          <button type="submit">Login</button>
        </form>
        <Link to="/registration">Registration</Link>
      </div>
    )
  }
}        
