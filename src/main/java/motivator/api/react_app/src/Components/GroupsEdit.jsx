import React, { Component } from 'react';
import axios from 'axios';

class GroupProfileEdit extends Component {

  state = {
    name: '',
    gitHubGrupRep: '',
    trelloGroup: '',
    slackGroupHook: ''
  };

  componentDidMount() {
    var self = this;
    var token = window.localStorage.getItem('Authorization');
    var config = {
      headers: {
        Authorization: "Bearer " + token
      }
    }
    axios.get(`http://127.0.0.1:8080/app/currentuser/groups/`, config)
      .then((result) => {
        if (result.status === 200) {
          var res = result.data;
          var newData = {
            name: res.name,
            gitHubGrupRep: res.gitHubGrupRep,
            trelloGroup: res.trelloGroup,
            slackGroupHook: res.slackGroupHook
          }
          self.setState(newData)
          console.log(res)
        }
      });
  }

  redir = (props) => {
    this.props.history.push('/groupProfile');
  };

  onChange = (event) => {
    const state = this.state
    state[event.target.name] = event.target.value;
    this.setState(state);
  }

  onSubmit = (event) => {
    event.preventDefault();
    var token = window.localStorage.getItem('Authorization');
    var config = {
      headers: {
        Authorization: "Bearer " + token
      }
    }
    const { name, gitHubGrupRep, trelloGroup, slackGroupHook } = this.state;
    axios.post(`http://127.0.0.1:8080/groupupdate`, { name, gitHubGrupRep, trelloGroup, slackGroupHook }, config)
      .then((response) => {
        console.log(response);
        this.setState(response);
        this.redir();
      })
      .catch(function (error) {
        console.log(error);
      });

  }

  render() {
    return (
      <div>
        <form onSubmit={this.onSubmit}>
          <label>
            Name:
          <input type='text' name='name' placeholder={this.state.name} onChange={this.onChange} />
          </label>
          <label>
            GitHub:
          <input type='text' name='gitHubGrupRep' placeholder='gitHubGrupRep' onChange={this.onChange} />
          </label>
          <label>
            New password:
          <input type='text' name='trelloGroup' placeholder='trelloGroup' onChange={this.onChange} />
          </label>
          <label>
            Repeat new password:
          <input type='text' name='slackGroupHook' placeholder='slackGroupHook' onChange={this.onChange} />
          </label>
          <button type="submit" className="btn btn-default" onClick = {this.onSubmit} >Submit</button>
        </form>
      </div>
    )
  }
}


export default GroupProfileEdit;