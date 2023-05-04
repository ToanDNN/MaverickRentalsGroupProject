// https://stackoverflow.com/questions/63124161/attempted-import-error-switch-is-not-exported-from-react-router-dom
import { BrowserRouter as Router, Routes , Route } from 'react-router-dom';
import Home from './Home';
import CreateReservation from './components/CreateReservation';
import Navbar from './Navbar';
import CheckIn from './components/CheckIn';
import Confirmation from './components/Confirmation';
import CouldBeUseful from './CouldBeUseful';
import EditReservationStart from './EditReservationStart';

function App() {
  return (
    <Router>
      <Navbar/>
      <Routes>
        <Route path="/" element={<Home/>} />
        <Route path="/create-reservation" element={<CreateReservation />} />
        <Route path="/outdated-reservation" element={<CouldBeUseful/>} />
        <Route path="/edit-reservation" element={<EditReservationStart/>} />
        <Route path="/checkin" element={<CheckIn/>} />
        <Route path="/confirmation" element={<Confirmation/>} />
      </Routes>
    </Router>
  );
}

export default App;
