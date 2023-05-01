import { configureStore } from '@reduxjs/toolkit';
import userReducer from '../features/user/userSlice';
import { apiSlice } from '../features/api/apiSlice';
import userProfileReducer from '../features/userProfile/userProfileSlice';
import storage from 'redux-persist/lib/storage';
import { persistStore, persistReducer, FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER, PersistConfig } from 'redux-persist';
import persistCombineReducers from 'redux-persist/es/persistCombineReducers';

const persistConfig = {
    key: 'main-root',
    storage,
    whitelist: ['userProfile']
    // blacklist: [apiSlice]
};

const reducers = {
    user: userReducer,
    [apiSlice.reducerPath]: apiSlice.reducer,
    userProfile: userProfileReducer
};

const rootReducer = persistCombineReducers(persistConfig, reducers)

// const persistedReducer = persistReducer(persistConfig, rootReducer);

const store = configureStore({
    reducer: rootReducer,
    // middleware: apiSlice.middleware
    middleware: getDefaultMiddleware =>
        getDefaultMiddleware({
            serializableCheck: {
                ignoreActions: [REHYDRATE],
            }
        }).concat(apiSlice.middleware)
})

const Persistor = persistStore(store);

export { Persistor };
export default store;