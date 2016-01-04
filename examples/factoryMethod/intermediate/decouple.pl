
java_import(['factoryMethod.intermediate', 'javax.swing']).

declareSet(creators, ['LabelCreatorImpl.createLabel__void', 'ButtonCreatorImpl.createButton__void']).
declareSet(products, ['JLabel', 'JButton']).

friendOf('LabelCreatorImpl', 'LabelCreatorImpl.createLabel__void').
friendOf('ButtonCreatorImpl', 'ButtonCreatorImpl.createButton__void').


hideSet(creators).
hideSetButFrom(products, creators).
friendOf(['javax.swing', 'LabelCreator', 'ButtonCreator'], products).
