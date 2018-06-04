// /groups/:id/github

import React, { Component } from 'react';
import axios from 'axios';

class Github extends Component {
  constructor () {
    super();
    this.state = {
      response: ''
    };
  }

  getGithubInfo () {
    var token = window.localStorage.getItem(`Authorization`);
    var config = {
      headers: {
        Authorization: `Bearer ` + token
      }
    };
    var self = this;
    axios.get(`http://127.0.0.1:8080/app/github`, config)
      .then(function (response) {
        console.log(response);
        self.setState({ response: response.data });
      })
      .catch(function (error) {
        console.log(error);
      });
  }

  componentWillMount () {
    this.getGithubInfo();
  }

  render () {
    return (
      <div>
        GitHub
      </div>
    );
  }
}

export default Github;
