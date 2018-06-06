import React, { Component } from 'react';

class Logout extends Component {
  componentDidMount () {
    setTimeout(2000);
    window.localStorage.clear();
    this.props.history.push('/login');
    console.log(this.props);
    console.log(window.localStorage);
  }

  render () {
    return (
      <h1 className='loading-text'>
        Logging out...
      </h1>
    );
  }
}

export default Logout;
