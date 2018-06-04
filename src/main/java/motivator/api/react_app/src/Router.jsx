import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import { ModalFooter } from 'react-bootstrap'; // Components
import Login from './Components/Login';
import UserProfile from './Components/UserProfile';
import UserProfileEdit from './Components/UserProfileEdit';
import Github from './Components/Github';
import Groups from './Components/Groups';
import GroupsCreate from './Components/GroupsCreate';
import Trello from './Components/Trello';
import Registration from './Components/Registration';
import Header from './Components/Header';

/* import GettingStarted from './Components/GettingStarted';
import GroupsEdit from './Components/GroupsEdit';
import GroupsMemberEdit from './Components/GroupsMemberEdit';
import GroupsMemberNew from './Components/GroupsMemberNew';
import GroupsProfile from './Components/GroupsProfile';
import PasswordForgotten from './Components/PasswordForgotten';
import PasswordReset from './Components/PasswordReset';
import Slack from './Components/Slack';
import Statistics from './Components/Statistics';
import GroupsLine from './Components/Groups/GroupsLine';
import GroupStatistics from './Components/Statistics/GroupStatistics';
import UserStatistics from './Components/Statistics/UserStatistics'; */

const Router = () => (
  <BrowserRouter>
    <div>
      <header>
        <Header />
      </header>
      <Switch>
        <Route path='/registration' component={Registration} />
        <Route path='/login' component={Login} />
        <Route path='/userProfile' exact component={UserProfile} />
        <Route path='/userProfileEdit' exact component={UserProfileEdit} />
        <Route path='/groupsCreate' component={GroupsCreate} />
        <Route path='/github' component={Github} />
        <Route path='/trello' exact component={Trello} />
        <Route path='/' exact component={Groups} />
        <Route render={() => <h3>Error 404 Custom Page</h3>} />
        {/* <Route path='/slack' exact component={Slack} />
        <Route path='/gettingStarted' component={GettingStarted} />
        <Route path='/groupsEdit' component={GroupsEdit} />
        <Route path='/groupsMemberEdit' component={GroupsMemberEdit} />
        <Route path='/groupsMemberNew' component={GroupsMemberNew} />
        <Route path='/groupsProfile' component={GroupsProfile} />
        <Route path='/passwordForgotten' exact component={PasswordForgotten} />
        <Route path='/passwordReset' exact component={PasswordReset} />
        <Route path='/statistics' exact component={Statistics} />       
        <Route path='/groupsLine' exact component={GroupsLine} />
        <Route path='/groupStatistics' exact component={GroupStatistics} />
        <Route path='/userStatistics' exact component={UserStatistics} /> */}
        
      </Switch>
      <ModalFooter>
        <p> Flow Academy - 2018 - Team BSBL &copy; - Motivator Project</p>
      </ModalFooter>
    </div>
  </BrowserRouter>
);

export default Router;

// RUN THESE COMMANDS TO USE IT:
// npm install -S react-router-bootstrap
// npm i react-bootstrap
// npm starttal indul
// npm install reactjs-popup --save
// npm i react-iframe
