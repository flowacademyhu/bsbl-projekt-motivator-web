import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import { Button, ButtonToolbar } from 'react-bootstrap';
import axios from 'axios';

class Header extends Component {
  state = {
    Groups: [],
    ActiveGroup: ''
  }

  componentWillMount = () => {
    this.getGroups()
  }

  redir = (props) => {
    this.props.history.push('/login');
  };

  getGroups = () => {
    var token = window.localStorage.getItem(`Authorization`);
    var config = {
      headers: {
        Authorization: `Bearer ` + token
      }
    };
    var self = this;

    axios.get(`http://127.0.0.1:8080/app/currentuser/activegroup`, config)
    .then((res) => {
      if (res.status === 200) {
        self.setState({Groups: res.data})
      /* } else {
        redir() */
      }
    })
  }

  setGroup = () => {
    var token = window.localStorage.getItem(`Authorization`);
    var config = {
      headers: {
        Authorization: `Bearer ` + token
      }
    };
    var activegroup = this.state.ActiveGroup;

    axios.post(`http://127.0.0.1:8080/app/currentuser/activegroup`, { activegroup }, config)
    .then((res) => {
      if (res.status === 200) {
        console.log('Success')
      }
    })
  }

  handleChange = (event) => {
    const state = this.state
    state[event.target.name] = event.target.value;
    this.setState(state);
    this.setGroup();
  }

  iterateGroups = () => {
    this.state.Groups.map((group) => {
      return <option value={group}>group</option>
    })
  }

  render () {
    return (
      <div className='Header'>
        <ButtonToolbar>
          <NavLink to='/groups'><Button bsStyle='primary'>Home</Button></NavLink>
          <select bsStyle='danger' onChange={this.handleChange}>
            <option selected disabled>Choose your group</option>
            {this.iterateGroups}
          </select>
          <NavLink to='/github'><Button bsStyle='success'>GitHub</Button></NavLink>
          <NavLink to='/slack'><Button bsStyle='info'>Slack</Button></NavLink>
          <NavLink to='/trello'><Button bsStyle='warning'>Trello</Button></NavLink>
          <NavLink to='/groupsprofile'><Button bsStyle='danger'>Group Profile</Button></NavLink>
          <NavLink to='/userprofile'><Button bsStyle='secondary'>User Profile</Button></NavLink>
          <NavLink to='/logout'><Button bsStyle='link'>Logout</Button></NavLink>
        </ButtonToolbar>
      </div>
    );
  }
}

export default Header;
