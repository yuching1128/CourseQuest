import { combineReducers, configureStore } from '@reduxjs/toolkit';
import coursesReducer from '../features/courses/coursesSlice';
import userReducer from '../features/user/userSlice';
import { apiSlice } from '../features/api/apiSlice';
import userProfileReducer from '../features/userProfile/userProfileSlice';
import storage from 'redux-persist/lib/storage';
import { persistStore, persistReducer, FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER } from 'redux-persist';

const persistConfig = {
    key: 'main-root',
    storage,
}

const rootReducer = combineReducers({
    courses: coursesReducer,
    user: userReducer,
    [apiSlice.reducerPath]: apiSlice.reducer,
    userProfile: userProfileReducer
})

const persistedReducer = persistReducer(persistConfig, rootReducer);

const store = configureStore({
    reducer: persistedReducer,
    middleware: getDefaultMiddleware =>
        getDefaultMiddleware({
            serializableCheck: {
                ignoreActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER],
            }
        }).concat(apiSlice.middleware)
})

const Persistor = persistStore(store);

export { Persistor };
export default store;