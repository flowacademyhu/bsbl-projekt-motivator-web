// /groups/:id/github

import React, { Component } from 'react';
import axios from 'axios';

class Github extends Component {
  constructor () {
    super();
    this.state = {
      commitShal: '',
      commitMessage: '',
      commitDate: ''
    };
  }

  componentWillMount () {
    var self = this;
    var token = window.localStorage.getItem('Authorization');
    var config = {
      headers: {
        Authorization: 'Bearer ' + token
      }
    };

    axios.get(`http://127.0.0.1:8080/app/github`, config)
      .then((response) => {
        if (response.status === 200 || response.status === 202) {
          var result = response.data;
          var newData = {
            commitShal: result.commitShal,
            commitMessage: result.commitMessage,
            commitDate: result.commitDate
          };

          self.setState(newData);
          console.log(result);
        }
      })
      .catch((error) => {
        console.log(error);
      });
  }

  render () {
    return (
      <div>
        {<span key={this.state.commitShal}>
          {this.state.commitShal}
          {this.state.commitMessage}
          {this.state.commitDate}
        </span>}
      </div>
    );
  }
}

export default Github;
