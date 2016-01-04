
java_import(['factoryMethod.candidate', 'javax.swing']).

declareSet(creators, ['GUIComponentCreator.createLabel__void', 'GUIComponentCreator.createButton__void']).
declareSet(products, ['JLabel', 'JButton']).

hideSet(creators).
hideSetButFrom(products, creators).
friendOf(['javax.swing'], products).
