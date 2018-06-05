// /groups/:id/github

import React, { Component } from 'react';
import axios from 'axios';

class Github extends Component {
  constructor () {
    super();
    this.state = {
      commits: []
    };
  }

  componentWillMount () {
    var token = window.localStorage.getItem(`Authorization`);
    var config = {
      headers: {
        Authorization: `Bearer ` + token
      }
    };
    var self = this;
    axios.get(`http://127.0.0.1:8080/app/github`, config)
      .then(function (response) {
        console.log('Commits:' + JSON.stringify(response.data));
        self.setState({ response: response.data.commits });
      })
      .catch(function (error) {
        console.log(error);
      });
  }

  render () {
    return (
      <div>
        { this.state.commits.map((commit) => <span key={commit.commitShal}> {commit.commitMessage} {commit.commitDate} </span>) }
      </div>
    );
  }
}

export default Github;
