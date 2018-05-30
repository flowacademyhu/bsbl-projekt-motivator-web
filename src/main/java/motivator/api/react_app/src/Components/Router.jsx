// /

import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import { ModalFooter } from 'react-bootstrap'; // Components
import Login from './Login';
import GettingStarted from './GettingStarted';
import Github from './Github';
import Groups from './Groups';
import GroupsCreate from './GroupsCreate';
import GroupsEdit from './GroupsEdit';
import GroupsMemberEdit from './GroupsMemberEdit';
import GroupsMemberNew from './GroupsMemberNew';
import GroupsProfile from './GroupsProfile';
import PasswordForgotten from './PasswordForgotten';
import PasswordReset from './PasswordReset';
import Slack from './Slack';
import Statistics from './Statistics';
import Trello from './Trello';
import UserProfile from './UserProfile';
import UserProfileEdit from './UserProfileEdit';
import Registration from './Registration';

const Router = () => (
  <BrowserRouter>
    <div>
      <Switch>
        <Route path='/registration' component={Registration} />
        <Route path='/login' component={Login} />
        <Route path='/gettingStarted' component={GettingStarted} />
        <Route path='/github' component={Github} />
        <Route path='/' component={Groups} />
        <Route path='/groupsCreate' component={GroupsCreate} />
        <Route path='/groupsEdit' component={GroupsEdit} />
        <Route path='/groupsMemberEdit' component={GroupsMemberEdit} />
        <Route path='/groupsMemberNew' component={GroupsMemberNew} />
        <Route path='/groupsProfile' component={GroupsProfile} />
        <Route path='/passwordForgotten' exact component={PasswordForgotten} />
        <Route path='/passwordReset' exact component={PasswordReset} />
        <Route path='/slack' exact component={Slack} />
        <Route path='/statistics' exact component={Statistics} />
        <Route path='/trello' exact component={Trello} />
        <Route path='/userProfile' exact component={UserProfile} />
        <Route path='/userProfileEdit' exact component={UserProfileEdit} />
      </Switch>
      <ModalFooter>
        <p> Flow Academy - 2018 - Team BSBL - Motivator Project</p>
      </ModalFooter>
    </div>
  </BrowserRouter>
);

export default Router;

/**/
// npm install -S react-router-bootstrap
// npm i react-bootstrap
// npm starttal indul
