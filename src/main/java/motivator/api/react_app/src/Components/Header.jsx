import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';

class Header extends Component {
  render () {
    return (
      <div className='Header'>
        <NavLink to='/'><button>Home</button></NavLink>
        <select>
          <option selected disabled>Choose your group</option>
          <option value='group_one'>Group One</option>
          <option value='group_two'>Group Two</option>
        </select>
        <NavLink to='/github'><button>GitHub</button></NavLink>
        <NavLink to='/slack'><button>Slack</button></NavLink>
        <NavLink to='/trello'><button>Trello</button></NavLink>
        <NavLink to='/groupProfile'><button>Group Profile</button></NavLink>
        <NavLink to='/userProfile'><button>User Profile</button></NavLink>
        <NavLink to='/logout'><button>Logout</button></NavLink>
      </div>
    );
  }
}

export default Header;
