// /groups/:id/trello

// WORKS IN CHROME, INSTALL THIS: https://chrome.google.com/webstore/detail/ignore-x-frame-headers/gleekbfjekiniecknbkamfmkohkpodhe?hl=en-US

import React, { Component } from 'react';
import Iframe from 'react-iframe';
import axios from 'axios';

class Trello extends Component {
  constructor () {
    super();
    this.state = {
      trelloUrl: 'https://trello.com/b/415XHD44/test-motivator-board'
    };
  }

  componentDidMount () {
    var token = window.localStorage.getItem('Authorization');
    var config = {
      headers: {
        Authorization: 'Bearer ' + token
      }
    };

    axios.get(`http://127.0.0.1:8080/app/currentuser/activegroup/trello`, config)
      .then((response) => {
        if (response.status === 200) {
          var result = response.data;
          this.setState({ trelloUrl: result });
          console.log(this.state.trelloUrl);
        } else {
          console.log('Cannot show active Trello board.');
        }
      });
  }

  render () {
    return (
      <Iframe url={this.state.trelloUrl} frameBorder='0' width='100%' height='800px' id='trelloId' className='trello' display='initial' positio='relative' allowFullScreen />
    );
  }
}

export default Trello;
