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
        self.setState({commits: res.data});
        console.log(self.state);
      });
  }

  render () {
    return this.state.commits.map((commit, i) => {
      return (
        <div key={commit.id}>
          <h4>Last GitHub commits</h4>
          <div>
            <div>{commit.commitShal}</div>
            <div>{commit.commitMessage}</div>
            <div>{commit.commitDate}</div>
          </div>
        </div>
      );
    });
  }
}

export default Github;
