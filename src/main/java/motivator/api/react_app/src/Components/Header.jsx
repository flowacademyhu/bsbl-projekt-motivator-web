import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import { Button, ButtonToolbar } from 'react-bootstrap';
import axios from 'axios';

class Header extends Component {
  state = {
    Groups: [],
    activeGroup: ''
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
          self.setState({ Groups: res.data })
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
    var self = this;
    var activeGroup = self.state.activeGroup;
    axios.post(`http://127.0.0.1:8080/app/currentuser/activegroup`, { activeGroup }, config)
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

  renderGroups = () => {
    return this.state.Groups.map((group, i) => {
      return (
        <option key={i} value={group}> {group} </option>
      );
    });
  }


  render() {
    var divStyle = {
      height: '190px',
      };
      var size = {
        marginTop: '50px',
        marginLeft: '10px',
      }

    if (window.localStorage.length !== 0) {
      return (
        <div className='Header' style={divStyle}>
          <ButtonToolbar>
            <NavLink to='/groups' style={size}><Button bsStyle='danger'>Home</Button></NavLink>
            <select bsStyle='danger' style={size} name='activeGroup' onChange={this.handleChange} value={this.state.activeGroup}>
              <option selected disabled>Choose your group</option>
              {this.renderGroups()}
            </select>
            <NavLink to='/github' style={size}><Button bsStyle='success'>GitHub</Button></NavLink>
            <NavLink to='/slack' style={size}><Button bsStyle='info'>Slack</Button></NavLink>
            <NavLink to='/trello' style={size}><Button bsStyle='warning'>Trello</Button></NavLink>
            <NavLink to='/groupsprofile' style={size}><Button bsStyle='primary'>Group Profile</Button></NavLink>
            <NavLink to='/userprofile' style={size}><Button bsStyle='secondary'>User Profile</Button></NavLink>
            <NavLink to='/logout' style={size}><Button bsStyle='danger'>Logout</Button></NavLink>
          </ButtonToolbar>
        </div>
      );
    } else {
      return (
        <div> . </div>
      )
    }
  }
}

export default Header;
