import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import { Button, ButtonToolbar } from 'react-bootstrap';

class Header extends Component {
  render () {
    return (
      <div className='Header'>
        <ButtonToolbar>
          <NavLink to='/'><Button bsStyle='primary'>Home</Button></NavLink>
          <select bsStyle='danger'>
            <option selected disabled>Choose your group</option>
            <option value='group_one'>Group One</option>
            <option value='group_two'>Group Two</option>
          </select>
          <NavLink to='/github'><Button bsStyle='success'>GitHub</Button></NavLink>
          <NavLink to='/slack'><Button bsStyle='info'>Slack</Button></NavLink>
          <NavLink to='/trello'><Button bsStyle='warning'>Trello</Button></NavLink>
          <NavLink to='/groupsProfile'><Button bsStyle='danger'>Group Profile</Button></NavLink>
          <NavLink to='/userProfile'><Button bsStyle='secondary'>User Profile</Button></NavLink>
          <NavLink to='/logout'><Button bsStyle='link'>Logout</Button></NavLink>
        </ButtonToolbar>
      </div>
    );
  }
}

export default Header;
