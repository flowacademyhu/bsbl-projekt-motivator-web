// /groups/:id/trello

// WORKS IN CHROME, INSTALL THIS: https://chrome.google.com/webstore/detail/ignore-x-frame-headers/gleekbfjekiniecknbkamfmkohkpodhe?hl=en-US

import React, { Component } from 'react';
import Iframe from 'react-iframe';

class Trello extends Component {
  render () {
    return (
      <Iframe url='https://trello.com/b/415XHD44/test-motivator-board' frameBorder='0' width='100%' height='800px' id='trelloId' className='trello' display='initial' positio='relative' allowFullScreen />
    );
  }
}

export default Trello;
