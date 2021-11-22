import { createStore, applyMiddleware } from 'redux';
import thunkMiddleware from 'redux-thunk';
import rootReducer from './reducers'

const initialState = {};

let store = createStore(
  rootReducer,
  initialState,
  applyMiddleware(thunkMiddleware)
);

export default store;