// /groups/:id/github

import React, { Component } from 'react';
import fetch from 'isomorphic-fetch';

class Github extends Component {
  constructor (props) {
    super(props);
    this.state = {
      commits: [{
        id: 1,
        commitShal: 'test1',
        commitMessage: 'test2',
        commitDate: 'test3'
      }]
    };
  }

  componentDidMount (props) {
    var token = window.localStorage.getItem('Authorization');
    var config = {
      headers: {
        Authorization: 'Bearer ' + token
      }
    };
    fetch(`http://127.0.0.1:8080/github`, config)
      .then(response => {
        var newData = {
          id: response.id,
          commitShal: response.commitShal,
          commitMessage: response.commitMessage,
          commitDate: response.commitDate
        };
        this.setState(newData);
      });
  }

  render () {
    console.log(this.state.commits);
    return this.state.commits.map((commit, i) => (
      <div key={i}>
        <h2>Last GitHub commits</h2>
        <div>
          <div>{commit.commitShal}</div>
          <div>{commit.commitMessage}</div>
          <div>{commit.commitDate}</div>
        </div>
      </div>
    )
    );
  }
}

export default Github;
