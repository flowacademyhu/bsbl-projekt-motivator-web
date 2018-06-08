import React, { Component } from 'react';
import axios from 'axios';

class Github extends Component {
  constructor (props) {
    super(props);
    this.state = {
      commits: []
    };
  }

  componentWillMount (props) {
    var token = window.localStorage.getItem('Authorization');
    var config = {
      headers: {
        Authorization: 'Bearer ' + token
      }
    };
    var self = this;
    axios.get(`http://127.0.0.1:8080/app/github`, config)
      .then((res) => {
        self.setState({ commits: res.data });
        console.log(self.state);
      });
  }

  render () {
    return this.state.commits.map((commit) => {
      return (
        <div >
          <h3>Last GitHub commits</h3>
          <div key={commit.id}>
            <h6>Commit identification (shal)</h6>
            <div>{commit.commitShal}</div>
            <h6>Commit message</h6>
            <div>{commit.commitMessage}</div>
            <h6>Commit date</h6>
            <div>{commit.commitDate}</div>
            <h6>Changed files</h6>
            <div>{commit.changes}</div>
          </div>
        </div>
      );
    });
  }
}

export default Github;
