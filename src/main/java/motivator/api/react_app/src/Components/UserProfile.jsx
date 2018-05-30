import React, { Component } from 'react';
import axios from 'axios';
import JSON from './userProfiles.json';

class User extends Component {
  state = {
    users: JSON
  }

  render() {
    const details = this.state.map((user) => {
      return (
        <div>
          <JSON key={user.id} detail={user} />
        </div>
      );
    }
    )
  };
};

export default User;
