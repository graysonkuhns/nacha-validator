import React from 'react';
import FileView from './pages/FileView';
import DetailView from './pages/DetailView';

export default function App() {
  return (
    <div style={{
      height: '100%',
      width: '70%',
      marginLeft: '15%',
      marginRight: '15%',
      boxShadow: '5px 10px',
    }}>
      <FileView />
      <DetailView />
    </div>
  );
}
