import "./App.css";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect,
} from "react-router-dom";
import UserLayout from "./layout/UserLayout";
import Navbars from "./component/Navbars";
import "bootstrap/dist/css/bootstrap.min.css";

function App() {
  return (
    <Router>
      <Navbars />
      <Switch>
        {/* <Route path="/login" render={(props) => <Login {...props} />} /> */}
        <Route path="/user" render={(props) => <UserLayout {...props} />} />
        <Redirect from="/" to="/user/char" />
      </Switch>
    </Router>
  );
}

export default App;
