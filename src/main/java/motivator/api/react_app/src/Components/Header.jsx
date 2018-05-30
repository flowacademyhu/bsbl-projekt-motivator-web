import React from 'react';
import { Link } from 'react-router-dom';

class Header extends React.Component {
  render() {
    return (
      <div className="Header">
        <Link to="/home"><button>Home</button></Link>
        <select>
          <option value="volvo">Volvo</option>
        </select>
        <Link to="/github"><button>Github</button></Link>
        <Link to="/slack"><button>Slack</button></Link>
        <Link to="/trello"><button>Trello</button></Link>
        <Link to="/groupProfile"><button>Group Profile</button></Link>
        <Link to="/userProfile"><button>User Profile</button></Link>
        <Link to="/logout"><button>Logout</button></Link>
      </div>
    );
  }
}
