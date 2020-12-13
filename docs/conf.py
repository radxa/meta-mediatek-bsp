# Configuration file for the Sphinx documentation builder.
#
# This file only contains a selection of the most common options. For a full
# list see the documentation:
# https://www.sphinx-doc.org/en/master/usage/configuration.html

# -- Project information -----------------------------------------------------

project = 'RITY BSP'
copyright = '2020, Fabien Parent <fparent@baylibre.com>'
author = 'Fabien Parent <fparent@baylibre.com>'

# The full version, including alpha/beta/rc tags
release = 'rity-v20.2'

# -- General configuration ---------------------------------------------------

# Add any Sphinx extension module names here, as strings. They can be
# extensions coming with Sphinx (named 'sphinx.ext.*') or your custom
# ones.
extensions = [
    'sphinxcontrib.mermaid',
    'sphinx.ext.autosectionlabel',
    'sphinx-prompt',
]

# -- Options for HTML output -------------------------------------------------

# The theme to use for HTML and HTML Help pages.  See the documentation for
# a list of builtin themes.
#
html_theme = 'sphinx_rtd_theme'

# Mermaid configuration
mermaid_params = [
    '--theme', 'forest',
    '--width', '600',
    '--backgroundColor', 'transparent'
]

autosectionlabel_prefix_document = True
