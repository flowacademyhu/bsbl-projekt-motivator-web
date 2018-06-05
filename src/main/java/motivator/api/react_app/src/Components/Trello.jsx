// /groups/:id/trello

// WORKS IN CHROME, INSTALL THIS: https://chrome.google.com/webstore/detail/ignore-x-frame-headers/gleekbfjekiniecknbkamfmkohkpodhe?hl=en-US

import React, { Component } from 'react';
import Iframe from 'react-iframe';
import axios from 'axios';

class Trello extends Component {
  constructor (props) {
    super();
    this.state = {
      trelloUrl: '',
      disabled: false,
      options: props.options,
      value: null
    };
  }

  componentDidMount () {
    var token = window.localStorage.getItem(`Authorization`);
    var config = {
      headers: {
        Authorization: `Bearer ` + token
      }
    };
    var self = this;
    axios.get(`http://127.0.0.1:8080/app/currentuser/activegroup`, config)
      .then(function (response) {
        console.log(response);
        self.setState({ trelloUrl: response.data });
      })
      .catch(function (error) {
        console.log(error);
      });
  }

  render () {
    return (
      <Iframe url={this.state.trelloUrl} frameBorder='0' width='100%' height='800px' id='trelloId' className='trello' display='initial' positio='relative' allowFullScreen />
    );
  }
}

export default Trello;
