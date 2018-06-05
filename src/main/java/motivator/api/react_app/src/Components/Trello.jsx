// /groups/:id/trello

import React, { Component } from 'react';
import Iframe from 'react-iframe';

class Trello extends Component {
  render () {
    return (
      <Iframe url=' ' frameBorder='0' width='100%' height='800px' id='trelloId' className='trello' display='initial' positio='relative' allowFullScreen />
    );
  }
}

export default Trello;
