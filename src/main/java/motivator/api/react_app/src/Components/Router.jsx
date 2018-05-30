// /

import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import { ModalFooter } from 'react-bootstrap'; // Components
import Login from './Components/Login';
import GettingStarted from './Components/GettingStarted';
import Github from './Components/Github';
import Groups from './Components/Groups';
import GroupsCreate from './Components/GroupsCreate';
import GroupsEdit from './Components/GroupsEdit';
import GroupsMemberEdit from './Components/GroupsMemberEdit';
import GroupsMemberNew from './Components/GroupsMemberNew';
import GroupsProfile from './Components/GroupsProfile';
import PasswordForgotten from './Components/PasswordForgotten';
import PasswordReset from './Components/PasswordReset';
import Slack from './Components/Slack';
import Statistics from './Components/Statistics';
import Trello from './Components/Trello';
import UserProfile from './Components/UserProfile';
import UserProfileEdit from './Components/UserProfileEdit';
import Registration from './Components/Registration';
import GroupsLine from './Components/Groups/GroupsLine';
import GroupStatistics from './Components/Statistics/GroupStatistics';
import UserStatistics from './Components/Statistics/UserStatistics';

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
        <Route path='/groupsLine' exact component={GroupsLine} />
        <Route path='/groupStatistics' exact component={GroupStatistics} />
        <Route path='/userStatistics' exact component={UserStatistics} />
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
