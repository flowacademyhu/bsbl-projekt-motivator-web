import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

export default class Login extends Component {
  constructor(props) {
    super(props);
  };

  state = {
    email: '',
    password: '',
    token: ''
  };

  redir = (props) => {
    this.props.history.push('/');
  };

  handleChange = (event) => {
    const state = this.state
    state[event.target.name] = event.target.value;
    this.setState(state);
  }

  handleSubmit = event => {
    event.preventDefault();
    var email = this.state.email;
    var password = this.state.password;
    var authOptions = {
      method: `post`,
      url: `http://127.0.0.1:8080/login`,
      data: { email, password },
      headers: {
        Authorization: `Bearer ` + this.state.token
      }
    }

    return axios(authOptions)
      .then(res => {
        if (res.status === 200) {
          window.localStorage.setItem(`Authorization`, res.data)
          console.log(window.localStorage.getItem(`Authorization`));
          this.redir();
        }
      });
  }

  render() {
    return (
      <div>
        <form onSubmit={this.handleSubmit}>
          <label>
            E-mail adress:
            <input type='email' name='email' onChange={this.handleChange} />
          </label><br />
          <label>
            Password:
            <input type='password' name='password' onChange={this.handleChange} />
          </label><br />
          <button type='submit'>Login</button>
        </form>
        <Link type='button' to='/registration'>Registration</Link>
      </div>
    )
  }
};
